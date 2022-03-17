import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {taalBeheersingValues} from "../../../forms-util/formcontrol-value-enums";
import {Vacature} from "../../../entities/vacature/vacature";
import * as i18nIsoLanguages from "@cospired/i18n-iso-languages";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";

@Component({
  selector: 'app-taalbeheersing-match',
  templateUrl: './taalbeheersing-match.component.html',
  styleUrls: ['./taalbeheersing-match.component.css']
})
export class TaalbeheersingMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature | MPArbeidsmarktkwalificatie;

  taalbeheersingen?: {
    codeTaal?: string | undefined;
    codeNiveauTaalbeheersingMondeling?: string | number;
    codeNiveauTaalbeheersingSchriftelijk?: string | number;
    codeNiveauTaalbeheersingLezen?: string | number;
    codeNiveauTaalbeheersingLuisteren?: string | number;
  }[]


  constructor() {
  }

  ngOnInit(): void {
    i18nIsoLanguages.registerLocale(require("@cospired/i18n-iso-languages/langs/nl.json"));
    this.taalbeheersingen = this.getMappedValues()
  }

  private getMappedValues() {
    return this.data?.taalbeheersing?.map((
      {
        codeTaal,
        codeNiveauTaalbeheersingMondeling,
        codeNiveauTaalbeheersingSchriftelijk,
        codeNiveauTaalbeheersingLezen,
        codeNiveauTaalbeheersingLuisteren
      }) => {

      const codeTaalToName = codeTaal ? i18nIsoLanguages.getName(codeTaal, "nl") : undefined

      const mondelingMapped = selectToTextConvert(codeNiveauTaalbeheersingMondeling, taalBeheersingValues);
      const schriftelijkMapped = selectToTextConvert(codeNiveauTaalbeheersingSchriftelijk, taalBeheersingValues);
      const lezenMapped = selectToTextConvert(codeNiveauTaalbeheersingLezen, taalBeheersingValues);
      const luisterenMapped = selectToTextConvert(codeNiveauTaalbeheersingLuisteren, taalBeheersingValues);

      return {
        codeTaal: codeTaalToName,
        codeNiveauTaalbeheersingMondeling: mondelingMapped,
        codeNiveauTaalbeheersingSchriftelijk: schriftelijkMapped,
        codeNiveauTaalbeheersingLezen: lezenMapped,
        codeNiveauTaalbeheersingLuisteren: luisterenMapped
      }
    });
  }
}
