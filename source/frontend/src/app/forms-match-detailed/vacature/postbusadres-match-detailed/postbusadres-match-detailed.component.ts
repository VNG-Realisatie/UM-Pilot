import {Component, Input, OnInit} from '@angular/core';
import {Postbusadres} from "../../../entities/vacature/postbusadres";

@Component({
  selector: 'app-postbusadres-match-detailed',
  templateUrl: './postbusadres-match-detailed.component.html',
  styleUrls: ['./postbusadres-match-detailed.component.css']
})
export class PostbusadresMatchDetailedComponent implements OnInit {

  @Input()
  data?: Postbusadres;

  postbusnummer?: number;

  constructor() {
  }

  ngOnInit(): void {
    this.postbusnummer = this.data?.postbusadres?.postbusnummer;
  }

}
