import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {codeTypeArbeidscontractValues, codeTypeOvereenkomstValues} from "../../../forms-util/formcontrol-value-enums";
import {Vacature} from "../../../entities/vacature/vacature";
import {MPWerkzoekendeMatchBemiddelaar} from "../../../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";

@Component({
  selector: 'app-contractvorm-match',
  templateUrl: './contractvorm-match.component.html',
  styleUrls: ['./contractvorm-match.component.css']
})
export class ContractvormMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature | MPWerkzoekendeMatchBemiddelaar | Werkzoekende;

  contractvormen?: {
    codeTypeArbeidscontract: string | number | undefined;
    codeTypeOvereenkomst: string | number | undefined;
  }[];

  constructor() {
  }

  ngOnInit(): void {

    this.contractvormen = this.data?.contractvorm?.map(({codeTypeArbeidscontract, codeTypeOvereenkomst}) => {
      const mappedCodeArbeidscontract = selectToTextConvert(codeTypeArbeidscontract, codeTypeArbeidscontractValues);
      const mappedCodeOvereenkomst = selectToTextConvert(codeTypeOvereenkomst, codeTypeOvereenkomstValues)
      return {codeTypeArbeidscontract: mappedCodeArbeidscontract, codeTypeOvereenkomst: mappedCodeOvereenkomst};
    })
  }
}
