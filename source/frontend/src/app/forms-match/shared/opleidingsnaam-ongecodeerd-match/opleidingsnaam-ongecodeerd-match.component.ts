import {Component, Input, OnInit} from '@angular/core';
import {Opleidingsnaam} from "../../../entities/shared/opleidingsnaam";
import {OpleidingsnaamOngecodeerd} from "../../../entities/shared/opleidingsnaamOngecodeerd";

@Component({
  selector: 'app-opleidingsnaam-ongecodeerd-match',
  templateUrl: './opleidingsnaam-ongecodeerd-match.component.html',
  styleUrls: ['./opleidingsnaam-ongecodeerd-match.component.css']
})
export class OpleidingsnaamOngecodeerdMatchComponent implements OnInit {

  @Input()
  data?: Opleidingsnaam

  naamOpleidingOngecodeerd?: string;
  omschrijvingOpleiding?: string;


  constructor() {
  }

  ngOnInit(): void {
    const castedOpleidingsnaam = this.data as OpleidingsnaamOngecodeerd;
    this.naamOpleidingOngecodeerd = castedOpleidingsnaam.naamOpleidingOngecodeerd;
    this.omschrijvingOpleiding = castedOpleidingsnaam.omschrijvingOpleiding;


  }

}
