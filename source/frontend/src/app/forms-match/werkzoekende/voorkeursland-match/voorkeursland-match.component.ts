import {Component, Input, OnInit} from '@angular/core';
import {MPWerkzoekendeMatchBemiddelaar} from "../../../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";
import {Voorkeursland} from "../../../entities/werkzoekende/voorkeursland";
import * as i18nIsoCountries from "i18n-iso-countries";

@Component({
  selector: 'app-voorkeursland-match',
  templateUrl: './voorkeursland-match.component.html',
  styleUrls: ['./voorkeursland-match.component.css']
})
export class VoorkeurslandMatchComponent implements OnInit {

  @Input()
  data?: MPWerkzoekendeMatchBemiddelaar

  voorkeurslanden?: Voorkeursland[]

  constructor() {
  }

  ngOnInit(): void {
    i18nIsoCountries.registerLocale(require("i18n-iso-countries/langs/nl.json"));

    this.voorkeurslanden = this.data?.voorkeursland?.map(x => {
      return {landencodeIso: x.landencodeIso ? i18nIsoCountries.getName(x.landencodeIso, 'nl') : x.landencodeIso}
    })
  }

}
