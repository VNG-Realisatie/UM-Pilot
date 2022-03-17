import {Component, Input, OnInit} from '@angular/core';
import {Vacature} from "../../../entities/vacature/vacature";
import {ContactpersoonAfdeling} from 'src/app/entities/vacature/contactpersoonAfdeling';

@Component({
  selector: 'app-contactpersoon-match-detailed',
  templateUrl: './contactpersoon-match-detailed.component.html',
  styleUrls: ['./contactpersoon-match-detailed.component.css']
})
export class ContactpersoonMatchDetailedComponent implements OnInit {

  @Input()
  data?: Vacature;

  contactpersonen?: ContactpersoonAfdeling[]

  constructor() {
  }

  ngOnInit(): void {
    this.contactpersonen = this.data?.werkgever?.contactpersoon
  }
}
