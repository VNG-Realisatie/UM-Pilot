import {Component, Input, OnInit} from '@angular/core';
import {Beroepsnaam} from "../../../entities/shared/beroepsnaam";
import {BeroepsnaamOngecodeerd} from "../../../entities/shared/beroepsnaamOngecodeerd";

@Component({
  selector: 'app-beroepsnaam-ongecodeerd-match',
  templateUrl: './beroepsnaam-ongecodeerd-match.component.html',
  styleUrls: ['./beroepsnaam-ongecodeerd-match.component.css']
})
export class BeroepsnaamOngecodeerdMatchComponent implements OnInit {

  @Input()
  data?: Beroepsnaam;

  naamBeroepOngecodeerd?: string;

  constructor() {
  }

  ngOnInit(): void {
    const castedBeroepsnaam = this.data as BeroepsnaamOngecodeerd;
    this.naamBeroepOngecodeerd = castedBeroepsnaam?.naamBeroepOngecodeerd
  }

}
