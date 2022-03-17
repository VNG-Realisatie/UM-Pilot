import {Component, Input, OnInit} from '@angular/core';
import {AntwoordnummerAdres} from "../../../entities/vacature/antwoordnummerAdres";

@Component({
  selector: 'app-antwoordnummeradres-match-detailed',
  templateUrl: './antwoordnummeradres-match-detailed.component.html',
  styleUrls: ['./antwoordnummeradres-match-detailed.component.css']
})
export class AntwoordnummeradresMatchDetailedComponent implements OnInit {

  @Input()
  data?: AntwoordnummerAdres;

  antwoordnummer?: number;

  constructor() {
  }

  ngOnInit(): void {
    this.antwoordnummer = this.data?.antwoordnummeradres?.antwoordnummer;
  }

}
