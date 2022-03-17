import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from "@angular/forms";

@Component({
  selector: 'select-enum',
  templateUrl: './select-enum.component.html',
  styleUrls: ['./select-enum.component.css']
})
export class SelectEnumComponent implements OnInit {

  @Input() control?: FormControl;
  @Input() values?: Map<number | string, string>;

  constructor() {
  }

  ngOnInit(): void {
  }

}
