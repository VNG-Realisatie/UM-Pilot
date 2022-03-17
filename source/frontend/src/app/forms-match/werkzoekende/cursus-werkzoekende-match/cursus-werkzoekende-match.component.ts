import {Component, Input, OnInit} from '@angular/core';
import {MPCursus} from "../../../entities/werkzoekende/mPCursus";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";

@Component({
  selector: 'app-cursus-werkzoekende-match',
  templateUrl: './cursus-werkzoekende-match.component.html',
  styleUrls: ['./cursus-werkzoekende-match.component.css']
})
export class CursusWerkzoekendeMatchComponent implements OnInit {

  @Input()
  data?: MPArbeidsmarktkwalificatie

  cursussen?: MPCursus[]

  constructor() {
  }

  ngOnInit(): void {
    this.cursussen = this.data?.cursus
  }

}
