import {Component, Input, OnInit} from '@angular/core';
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";
import {Arbeidsmarktkwalificatie} from "../../../entities/werkzoekende/arbeidsmarktkwalificatie";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {codeWerkEnDenkniveauMinimaalValuesInteger} from "../../../forms-util/formcontrol-value-enums";

@Component({
  selector: 'app-arbeidsmarktkwalificatie-detailed',
  templateUrl: './arbeidsmarktkwalificatie-detailed.component.html',
  styleUrls: ['./arbeidsmarktkwalificatie-detailed.component.css']
})
export class ArbeidsmarktkwalificatieDetailedComponent implements OnInit {

  @Input()
  data?: Werkzoekende

  arbeidsmarktkwalificatie?: Arbeidsmarktkwalificatie;

  codeWerkEnDenkniveauWerkzoekende?: string | number;


  constructor() {
  }

  ngOnInit(): void {
    this.codeWerkEnDenkniveauWerkzoekende = selectToTextConvert(this.data?.arbeidsmarktkwalificatie?.codeWerkEnDenkniveauWerkzoekende, codeWerkEnDenkniveauMinimaalValuesInteger);
    this.arbeidsmarktkwalificatie = this.data?.arbeidsmarktkwalificatie;
  }

}
