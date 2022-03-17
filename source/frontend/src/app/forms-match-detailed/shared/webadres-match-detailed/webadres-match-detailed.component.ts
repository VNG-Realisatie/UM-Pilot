import {Component, Input, OnInit} from '@angular/core';
import {selectToTextConvert} from "../../../forms-util/select-to-text";
import {codeWebadresValues} from "../../../forms-util/formcontrol-value-enums";
import {Werkgever} from "../../../entities/vacature/werkgever";
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";

@Component({
  selector: 'app-webadres-match-detailed',
  templateUrl: './webadres-match-detailed.component.html',
  styleUrls: ['./webadres-match-detailed.component.css']
})
export class WebadresMatchDetailedComponent implements OnInit {

  @Input()
  data?: Werkgever | Werkzoekende;

  webadresses?: {
    codeWebadres?: string | number;
    url?: string
  }[] | undefined


  constructor() {

  }

  ngOnInit(): void {
    this.webadresses = this.data?.webadres?.map(({codeWebadres, url}) => {
      const mappedCodeWeb = selectToTextConvert(codeWebadres, codeWebadresValues);
      return {codeWebadres: mappedCodeWeb, url: url}
    })

  }
}
