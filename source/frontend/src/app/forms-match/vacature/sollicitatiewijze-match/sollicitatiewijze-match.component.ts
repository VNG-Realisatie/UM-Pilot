import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {sollicitatieWijzeValues} from "../../../forms-util/formcontrol-value-enums";
import {Vacature} from "../../../entities/vacature/vacature";

@Component({
  selector: 'app-sollicitatiewijze-match',
  templateUrl: './sollicitatiewijze-match.component.html',
  styleUrls: ['./sollicitatiewijze-match.component.css']
})
export class SollicitatiewijzeMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature;

  sollicitatiewijzen?: {
    codeSollicitatiewijze?: string | number
  }[];


  constructor() {
  }

  ngOnInit(): void {
    this.sollicitatiewijzen = this.data?.sollicitatiewijze?.map(({codeSollicitatiewijze}) => {
      const mappedCodeSollicitatiewijze = selectToTextConvert(codeSollicitatiewijze, sollicitatieWijzeValues);
      return {codeSollicitatiewijze: mappedCodeSollicitatiewijze};
    })

  }
}
