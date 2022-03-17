import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {MPWerkzoekende} from "../../../entities/werkzoekende/mPWerkzoekende";
import * as i18nIsoCountries from 'i18n-iso-countries';

@Component({
  selector: 'app-voorkeursland-form',
  templateUrl: './voorkeursland-form.component.html',
  styleUrls: ['./voorkeursland-form.component.css']
})
export class VoorkeurslandFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPWerkzoekende>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  countryValues = new Map();

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    i18nIsoCountries.registerLocale(require("i18n-iso-countries/langs/nl.json"));

    this.countryValues = new Map(Object.entries(i18nIsoCountries.getNames("nl", {select: "official"})))

    this.form = this.fb.group({
      voorkeursland: this.fb.array([]),
    });

    this.subscription.add(
      this.form.controls.voorkeursland.valueChanges.subscribe((value) => {
        this.valueChangeWerkzoekende.emit({
          voorkeursland: value
        });
      })
    );

    this.formReady.emit(this.t);
  }

  addVoorkeursland() {
    this.t.push(this.fb.group({
      landencodeIso: ['', [Validators.required]]
    }));
  }

  removeVoorkeursland(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.voorkeursland as FormArray;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
