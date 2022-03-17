import {Component, Input, OnInit} from '@angular/core';
import {Opleidingsnaam} from "../../../entities/shared/opleidingsnaam";
import {MPOpleidingsnaamOngecodeerd} from "../../../entities/vacature/mPOpleidingsnaamOngecodeerd";

@Component({
  selector: 'app-mp-opleidingsnaam-ongecodeerd',
  templateUrl: './mp-opleidingsnaam-ongecodeerd.component.html',
  styleUrls: ['./mp-opleidingsnaam-ongecodeerd.component.css']
})
export class MpOpleidingsnaamOngecodeerdComponent implements OnInit {

  @Input()
  data?: Opleidingsnaam

  naamOpleidingOngecodeerd?: string;


  constructor() {
  }

  ngOnInit(): void {
    const castedOpleidingsnaam = this.data as MPOpleidingsnaamOngecodeerd;
    this.naamOpleidingOngecodeerd = castedOpleidingsnaam.naamOpleidingOngecodeerd;
  }

}
