import {Component, Input, OnInit} from '@angular/core';
import {MPWerkzoekendeMatchBemiddelaar} from "../../../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";
import {indicatieTwoValues} from "../../../forms-util/formcontrol-value-enums";
import {selectToTextConvert} from "../../../forms-util/select-to-text";

@Component({
  selector: 'app-basic-information-werkzoekende-match',
  templateUrl: './basic-information-werkzoekende-match.component.html',
  styleUrls: ['./basic-information-werkzoekende-match.component.css']
})
export class BasicInformationWerkzoekendeMatchComponent implements OnInit {

  @Input()
  data?: MPWerkzoekendeMatchBemiddelaar | Werkzoekende;

  indicatieLdrRegistratie?: string | number;
  indicatieBeschikbaarheidContactgegevens?: string | number;

  constructor() {
  }

  ngOnInit(): void {
    this.indicatieLdrRegistratie = selectToTextConvert(this.data?.indicatieLdrRegistratie, indicatieTwoValues);
    this.indicatieBeschikbaarheidContactgegevens = selectToTextConvert(this.data?.indicatieBeschikbaarheidContactgegevens, indicatieTwoValues)
  }

}
