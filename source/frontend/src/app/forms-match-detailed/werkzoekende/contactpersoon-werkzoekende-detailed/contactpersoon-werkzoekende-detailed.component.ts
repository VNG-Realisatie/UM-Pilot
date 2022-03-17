import {Component, Input, OnInit} from '@angular/core';
import {ContactpersoonAfdeling} from 'src/app/entities/werkzoekende/contactpersoonAfdeling';
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";

@Component({
  selector: 'app-contactpersoon-werkzoekende-detailed',
  templateUrl: './contactpersoon-werkzoekende-detailed.component.html',
  styleUrls: ['./contactpersoon-werkzoekende-detailed.component.css']
})
export class ContactpersoonWerkzoekendeDetailedComponent implements OnInit {

  @Input()
  data?: Werkzoekende

  contactpersonen?: ContactpersoonAfdeling[]

  constructor() {
  }

  ngOnInit(): void {
    this.contactpersonen = this.data?.contactpersoon
  }

}
