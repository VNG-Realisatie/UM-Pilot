import {Component, Input, OnInit} from '@angular/core';
import {Telefoonnummer} from "../../../entities/shared/telefoonnummer";

@Component({
  selector: 'app-telefoon-match-detailed',
  templateUrl: './telefoon-match-detailed.component.html',
  styleUrls: ['./telefoon-match-detailed.component.css']
})
export class TelefoonMatchDetailedComponent implements OnInit {

  @Input()
  data?: Telefoonnummer[];


  constructor() {
  }

  ngOnInit(): void {
  }


}
