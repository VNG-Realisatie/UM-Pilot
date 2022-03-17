import {Component, Input, OnInit} from '@angular/core';
import {MPWerkzoekendeMatchBemiddelaar} from "../../../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {codeWerkEnDenkniveauMinimaalValuesInteger} from "../../../forms-util/formcontrol-value-enums";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";

@Component({
  selector: 'app-arbeidsmarktkwalificatie-match',
  templateUrl: './arbeidsmarktkwalificatie-match.component.html',
  styleUrls: ['./arbeidsmarktkwalificatie-match.component.css']
})
export class ArbeidsmarktkwalificatieMatchComponent implements OnInit {

  @Input()
  data?: MPWerkzoekendeMatchBemiddelaar; //TODO: FIX this, it is being used by vacature but doesnt work in OpleidingOngecodeerd

  arbeidsmarktkwalificatie?: MPArbeidsmarktkwalificatie;

  codeWerkEnDenkniveauWerkzoekende?: string | number

  constructor() {
  }

  ngOnInit(): void {
    this.codeWerkEnDenkniveauWerkzoekende = selectToTextConvert(this.data?.arbeidsmarktkwalificatie?.codeWerkEnDenkniveauWerkzoekende, codeWerkEnDenkniveauMinimaalValuesInteger)
    this.arbeidsmarktkwalificatie = this.data?.arbeidsmarktkwalificatie;
  }

}
