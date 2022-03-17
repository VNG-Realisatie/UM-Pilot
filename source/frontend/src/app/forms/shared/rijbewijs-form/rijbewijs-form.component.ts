import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {rijbewijsValues} from "../../../forms-util/formcontrol-value-enums";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MPVacature} from "../../../entities/vacature/mPVacature";
import {Subscription} from "rxjs";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";

@Component({
  selector: 'app-rijbewijs-form',
  templateUrl: './rijbewijs-form.component.html',
  styleUrls: ['./rijbewijs-form.component.css']
})
export class RijbewijsFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPArbeidsmarktkwalificatie>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  rijbewijsValues = rijbewijsValues;

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      rijbewijs: this.fb.array([])
    })

    this.subscription.add(
      this.form.controls.rijbewijs.valueChanges.subscribe((value) => {
        const entity = {
          rijbewijs: value
        };

        this.valueChangeVacature.emit(entity);
        this.valueChangeWerkzoekende.emit(entity);
      })
    );

    this.formReady.emit(this.t);
  }

  addRijbewijs() {
    if (this.t.length < rijbewijsValues.size) {
      this.t.push(this.fb.group({
        codeSoortRijbewijs: ['', [Validators.required]]
      }));
    }
  }

  removeRijbewijs(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.rijbewijs as FormArray;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
