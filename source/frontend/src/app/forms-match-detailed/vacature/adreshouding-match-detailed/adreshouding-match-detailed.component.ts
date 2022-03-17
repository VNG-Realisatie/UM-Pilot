import {Component, Input, OnInit} from '@angular/core';
import {Vacature} from "../../../entities/vacature/vacature";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {codeFunctieAdresValues} from "../../../forms-util/formcontrol-value-enums";
import {dateFormatterString} from "../../../util/dateformatter";
import {isAdresNederland} from "../../../entities/vacature/adresHouding";
import {AdresBuitenland} from 'src/app/entities/vacature/adresBuitenland';
import {AdresNederland} from 'src/app/entities/vacature/adresNederland';

@Component({
  selector: 'app-adreshouding-match-detailed',
  templateUrl: './adreshouding-match-detailed.component.html',
  styleUrls: ['./adreshouding-match-detailed.component.css']
})
export class AdreshoudingMatchDetailedComponent implements OnInit {

  @Input()
  data?: Vacature;

  adreshoudingen?: {
    codeFunctieAdres?: string | number;
    datumAanvangAdres?: string;
    datumEindeAdres?: string;
    adres?: AdresNederland | AdresBuitenland;
  }[]

  constructor() {
  }

  ngOnInit(): void {
    this.adreshoudingen = this.data?.werkgever?.adresHouding?.map(({
                                                                     codeFunctieAdres,
                                                                     datumAanvangAdres,
                                                                     datumEindeAdres,
                                                                     adres
                                                                   }) => {
      const mappedCodeFunctie = selectToTextConvert(codeFunctieAdres, codeFunctieAdresValues);
      const mappedAanvang = dateFormatterString(datumAanvangAdres);
      const mappedEinde = dateFormatterString(datumEindeAdres);
      return {
        codeFunctieAdres: mappedCodeFunctie,
        datumAanvangAdres: mappedAanvang,
        datumEindeAdres: mappedEinde,
        adres: adres
      }
    });
  }


  checkControl(adres: AdresNederland | AdresBuitenland | undefined): boolean {
    if (adres) {
      return isAdresNederland(adres);
    } else {
      return false;
    }
  }
}
