import {Component, Input, OnInit} from '@angular/core';
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {codeWerkEnDenkniveauMinimaalValues, indicatieTwoValues} from "../../../forms-util/formcontrol-value-enums";
import {dateFormatterString} from "../../../util/dateformatter";
import {Vacature} from "../../../entities/vacature/vacature";

@Component({
  selector: 'app-basic-information-match-detailed',
  templateUrl: './basic-information-match-detailed.component.html',
  styleUrls: ['./basic-information-match-detailed.component.css']
})
export class BasicInformationMatchDetailedComponent implements OnInit {

  @Input()
  data?: Vacature;

  idVacature?: string
  naamVacature?: string
  nummerVacature?: string
  omschrijvingVacature?: string
  codeWerkEnDenkniveauMinimaal?: string | number
  indicatieLdrRegistratie?: string | number
  sluitingsDatumVacature?: string


  constructor() {
  }

  ngOnInit(): void {

    this.idVacature = this.data?.idVacature;
    this.naamVacature = this.data?.naamVacature;
    this.nummerVacature = this.data?.nummerVacature;
    this.omschrijvingVacature = this.data?.omschrijvingVacature;
    this.codeWerkEnDenkniveauMinimaal = selectToTextConvert(this.data?.codeWerkEnDenkniveauMinimaal, codeWerkEnDenkniveauMinimaalValues);
    this.indicatieLdrRegistratie = selectToTextConvert(this.data?.indicatieLdrRegistratie, indicatieTwoValues);
    this.sluitingsDatumVacature = dateFormatterString(this.data?.sluitingsDatumVacature);
  }


}
