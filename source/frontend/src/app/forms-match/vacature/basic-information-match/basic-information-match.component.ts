import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {codeWerkEnDenkniveauMinimaalValues, indicatieTwoValues} from "../../../forms-util/formcontrol-value-enums";
import {dateFormatterString} from "../../../util/dateformatter";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {Vacature} from "../../../entities/vacature/vacature";

@Component({
  selector: 'app-basic-information-match',
  templateUrl: './basic-information-match.component.html',
  styleUrls: ['./basic-information-match.component.css'],
})

export class BasicInformationMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature;

  codeWerkEnDenkniveauMinimaal?: string | number;
  indicatieLdrRegistratie?: string | number;
  sluitingsDatumVacature?: string;

  constructor() {
  }

  ngOnInit(): void {

    this.codeWerkEnDenkniveauMinimaal = selectToTextConvert(this.data?.codeWerkEnDenkniveauMinimaal, codeWerkEnDenkniveauMinimaalValues);
    this.indicatieLdrRegistratie = selectToTextConvert(this.data?.indicatieLdrRegistratie, indicatieTwoValues);
    this.sluitingsDatumVacature = dateFormatterString(this.data?.sluitingsDatumVacature);


  }

}
