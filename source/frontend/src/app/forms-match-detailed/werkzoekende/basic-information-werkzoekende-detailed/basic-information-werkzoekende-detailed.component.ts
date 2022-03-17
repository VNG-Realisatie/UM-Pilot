import {Component, Input, OnInit} from '@angular/core';
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {indicatieTwoValues} from "../../../forms-util/formcontrol-value-enums";

@Component({
  selector: 'app-basic-information-werkzoekende-detailed',
  templateUrl: './basic-information-werkzoekende-detailed.component.html',
  styleUrls: ['./basic-information-werkzoekende-detailed.component.css']
})
export class BasicInformationWerkzoekendeDetailedComponent implements OnInit {

  @Input()
  data?: Werkzoekende;

  indicatieLdrRegistratie?: string | number
  indicatieBeschikbaarheidContactgegevens?: string | number
  persoonlijkePresentatie?: string

  constructor() {
  }

  ngOnInit(): void {
    this.indicatieLdrRegistratie = selectToTextConvert(this.data?.indicatieLdrRegistratie, indicatieTwoValues);
    this.indicatieBeschikbaarheidContactgegevens = selectToTextConvert(this.data?.indicatieBeschikbaarheidContactgegevens, indicatieTwoValues);
    this.persoonlijkePresentatie = this.data?.persoonlijkePresentatie

  }

}
