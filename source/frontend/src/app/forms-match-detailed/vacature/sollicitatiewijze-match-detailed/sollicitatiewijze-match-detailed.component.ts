import {Component, Input, OnInit} from '@angular/core';
import {AbstractControl, FormControl} from "@angular/forms";
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {codeWebadresValues, sollicitatieWijzeValues} from "../../../forms-util/formcontrol-value-enums";
import {Vacature} from "../../../entities/vacature/vacature";

@Component({
  selector: 'app-sollicitatiewijze-match-detailed',
  templateUrl: './sollicitatiewijze-match-detailed.component.html',
  styleUrls: ['./sollicitatiewijze-match-detailed.component.css']
})
export class SollicitatiewijzeMatchDetailedComponent implements OnInit {

  @Input()
  data?: Vacature;

  sollicitatiewijzen?: {
    codeSollicitatiewijze?: string | number;
    webadres: {
      url?: string;
      codeWebadres?: string | number;
    };
  }[]


  constructor() {
  }

  ngOnInit(): void {
    this.sollicitatiewijzen = this.data?.sollicitatiewijze?.map(({codeSollicitatiewijze, webadres}) => {
      const mappedCodeSollicitatiewijze = selectToTextConvert(codeSollicitatiewijze, sollicitatieWijzeValues);
      const mappedCodeWeb = selectToTextConvert(webadres?.codeWebadres, codeWebadresValues);
      return {
        codeSollicitatiewijze: mappedCodeSollicitatiewijze,
        webadres: {url: webadres?.url, codeWebadres: mappedCodeWeb}
      };
    })


  }


  getWebadresFields(control: AbstractControl) {
    return control.get('webadres') as FormControl;
  }

}
