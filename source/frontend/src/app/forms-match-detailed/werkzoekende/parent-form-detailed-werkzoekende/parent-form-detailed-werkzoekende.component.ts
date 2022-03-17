import {Component, Input, OnInit} from '@angular/core';
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";

@Component({
  selector: 'app-parent-form-detailed-werkzoekende',
  templateUrl: './parent-form-detailed-werkzoekende.component.html',
  styleUrls: ['./parent-form-detailed-werkzoekende.component.css']
})
export class ParentFormDetailedWerkzoekendeComponent implements OnInit {

  @Input()
  werkzoekende?: Werkzoekende

  constructor() {
  }

  ngOnInit(): void {
  }

}
