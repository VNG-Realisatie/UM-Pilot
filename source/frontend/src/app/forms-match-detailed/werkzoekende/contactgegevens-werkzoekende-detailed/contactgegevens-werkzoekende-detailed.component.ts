import {Component, Input, OnInit} from '@angular/core';
import {Emailadres} from 'src/app/entities/shared/emailadres';
import {Telefoonnummer} from 'src/app/entities/shared/telefoonnummer';
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";

@Component({
  selector: 'app-contactgegevens-werkzoekende-detailed',
  templateUrl: './contactgegevens-werkzoekende-detailed.component.html',
  styleUrls: ['./contactgegevens-werkzoekende-detailed.component.css']
})
export class ContactgegevensWerkzoekendeDetailedComponent implements OnInit {

  @Input()
  data?: Werkzoekende

  emailadresses?: Emailadres[]
  telefoonnummers?: Telefoonnummer[]

  constructor() {
  }

  ngOnInit(): void {

    this.emailadresses = this.data?.emailadres;
    this.telefoonnummers = this.data?.telefoonnummer;
  }

}
