import {Component, Input, OnInit} from '@angular/core';
import {Beroepsnaam} from "../../../entities/shared/beroepsnaam";
import {BeroepsnaamGecodeerd} from "../../../entities/shared/beroepsnaamGecodeerd";

@Component({
  selector: 'app-beroepsnaam-gecodeerd-match',
  templateUrl: './beroepsnaam-gecodeerd-match.component.html',
  styleUrls: ['./beroepsnaam-gecodeerd-match.component.css']
})
export class BeroepsnaamGecodeerdMatchComponent implements OnInit {

  @Input()
  data?: Beroepsnaam;

  codeBeroepsnaam?: string
  omschrijvingBeroepsnaam?: string

  constructor() {
  }

  ngOnInit(): void {
    const castedBeroepsnaam = this.data as BeroepsnaamGecodeerd;
    this.codeBeroepsnaam = castedBeroepsnaam?.codeBeroepsnaam;
    this.omschrijvingBeroepsnaam = castedBeroepsnaam.omschrijvingBeroepsnaam;
  }

}
