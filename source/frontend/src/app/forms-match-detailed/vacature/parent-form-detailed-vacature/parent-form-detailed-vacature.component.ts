import {Component, Input, OnInit} from '@angular/core';
import {Vacature} from "../../../entities/vacature/vacature";

@Component({
  selector: 'app-parent-form-detailed-vacature',
  templateUrl: './parent-form-detailed-vacature.component.html',
  styleUrls: ['./parent-form-detailed-vacature.component.css']
})
export class ParentFormDetailedVacatureComponent implements OnInit {

  @Input()
  vacature?: Vacature;

  constructor() {
  }

  ngOnInit(): void {
  }

}
