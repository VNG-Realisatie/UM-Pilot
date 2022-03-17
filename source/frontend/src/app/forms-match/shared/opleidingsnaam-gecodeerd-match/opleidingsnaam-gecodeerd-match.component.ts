import {Component, Input, OnInit} from '@angular/core';
import {OpleidingsnaamGecodeerd} from "../../../entities/shared/opleidingsnaamGecodeerd";
import {Opleidingsnaam} from "../../../entities/shared/opleidingsnaam";

@Component({
  selector: 'app-opleidingsnaam-gecodeerd-match',
  templateUrl: './opleidingsnaam-gecodeerd-match.component.html',
  styleUrls: ['./opleidingsnaam-gecodeerd-match.component.css']
})
export class OpleidingsnaamGecodeerdMatchComponent implements OnInit {

  @Input()
  data?: Opleidingsnaam


  codeOpleidingsnaam?: number;
  omschrijvingOpleidingsnaam?: string;

  constructor() {
  }

  ngOnInit(): void {
    const castedOpleidingsnaam = this.data as OpleidingsnaamGecodeerd;
    this.codeOpleidingsnaam = castedOpleidingsnaam.codeOpleidingsnaam;
    this.omschrijvingOpleidingsnaam = castedOpleidingsnaam.omschrijvingOpleidingsnaam;

  }

}
