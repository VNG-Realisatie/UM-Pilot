import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {Subscription} from "rxjs";
import {MPVacature} from "../../../entities/vacature/mPVacature";

@Component({
  selector: 'app-cursus-form',
  templateUrl: './cursus-form.component.html',
  styleUrls: ['./cursus-form.component.css']
})
export class CursusFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      cursus: this.fb.array([])
    })

    this.subscription.add(
      this.form.controls.cursus.valueChanges.subscribe((value) => {
        this.valueChangeVacature.emit({
          cursus: value
        });
      })
    );

    this.formReady.emit(this.t);
  }

  addCursus() {
    this.t.push(this.fb.group({
      naamCursus: ['', [Validators.required, Validators.maxLength(120)]]
    }));
  }

  removeCursus(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.cursus as FormArray;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
