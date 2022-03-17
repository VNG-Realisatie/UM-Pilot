import {Component, Input, OnInit, ViewChild} from '@angular/core';
import * as i18nIsoLanguages from '@cospired/i18n-iso-languages';
import {merge, Observable, OperatorFunction, Subject} from "rxjs";
import {debounceTime, distinctUntilChanged, filter, map} from "rxjs/operators";
import {NgbTypeahead, NgbTypeaheadSelectItemEvent} from "@ng-bootstrap/ng-bootstrap";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-taal-typeahead',
  templateUrl: './taal-typeahead.component.html',
  styleUrls: ['./taal-typeahead.component.css']
})
export class TaalTypeaheadComponent implements OnInit {

  @Input() control?: FormControl

  @ViewChild('instance', {static: true}) instance!: NgbTypeahead;
  focus$ = new Subject<string>();
  click$ = new Subject<string>();

  constructor() {
  }

  iso639List: string[] = [];
  iso639Map: Map<string, string> = new Map([]);

  ngOnInit(): void {

    i18nIsoLanguages.registerLocale(require("@cospired/i18n-iso-languages/langs/nl.json"));
    this.obtainIso639()
  }

  private obtainIso639() {
    this.iso639List = [];
    this.iso639Map = new Map<string, string>();
    const languages = i18nIsoLanguages.getNames("nl")

    for (const key in languages) {
      if (languages.hasOwnProperty(key)) {
        const alpha3Code = i18nIsoLanguages.alpha2ToAlpha3T(key);
        if (alpha3Code) {
          this.iso639List.push(languages[key])
          this.iso639Map.set(languages[key], alpha3Code)
        }
      }
    }
  }

  search: OperatorFunction<string, readonly string[]> = (text$: Observable<string>) => {
    const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged());
    const clicksWithClosedPopup$ = this.click$.pipe(filter(() => this.instance && !this.instance.isPopupOpen()));
    const inputFocus$ = this.focus$;

    return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
      map(term => (term === '' ? this.iso639List
        : this.iso639List.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
    );
  }

  processTypeahead(event: NgbTypeaheadSelectItemEvent<any>) {
    const languageCode = this.iso639Map.get(event.item);
    this.control?.setValue(languageCode);
  }

}
