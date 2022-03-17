import {Component, Input, OnInit} from '@angular/core';
import {Emailadres} from "../../../entities/shared/emailadres";

@Component({
  selector: 'app-email-match-detailed',
  templateUrl: './email-match-detailed.component.html',
  styleUrls: ['./email-match-detailed.component.css']
})
export class EmailMatchDetailedComponent implements OnInit {

  @Input()
  data?: Emailadres[];

  constructor() {
  }

  ngOnInit(): void {
  }

}
