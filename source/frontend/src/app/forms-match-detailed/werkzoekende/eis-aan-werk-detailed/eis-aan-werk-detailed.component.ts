import {Component, Input, OnInit} from '@angular/core';
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";
import {indicatieThreeValues} from "../../../forms-util/formcontrol-value-enums";
import {selectToTextConvert} from "../../../forms-util/select-to-text";

@Component({
  selector: 'app-eis-aan-werk-detailed',
  templateUrl: './eis-aan-werk-detailed.component.html',
  styleUrls: ['./eis-aan-werk-detailed.component.css']
})
export class EisAanWerkDetailedComponent implements OnInit {

  @Input()
  data?: Werkzoekende;

  indicatieAanpassingWerkomgeving?: string | number;
  indicatieBegeleiding?: string | number;
  indicatieWerkvariatie?: string | number;

  constructor() {
  }

  ngOnInit(): void {
    this.indicatieAanpassingWerkomgeving = selectToTextConvert(this.data?.eisAanWerk?.indicatieAanpassingWerkomgeving, indicatieThreeValues);
    this.indicatieBegeleiding = selectToTextConvert(this.data?.eisAanWerk?.indicatieBegeleiding, indicatieThreeValues);
    this.indicatieWerkvariatie = selectToTextConvert(this.data?.eisAanWerk?.indicatieWerkvariatie, indicatieThreeValues)
  }

}
