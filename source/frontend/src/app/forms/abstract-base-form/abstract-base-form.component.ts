import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-abstract-base-form',
  templateUrl: './abstract-base-form.component.html',
  styleUrls: ['./abstract-base-form.component.css']
})
export class AbstractBaseFormComponent implements OnInit {

  form!: FormGroup;

  constructor() {
  }

  ngOnInit(): void {
  }

  get f() {
    return this.form.controls
  }

  getControl(controlName: string): FormControl {
    return this.form.get(controlName) as FormControl
  }

}
