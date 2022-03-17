import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {codeNiveauOpleidingValues, indicatieDiplomaValues} from "../../../forms-util/formcontrol-value-enums";
import {MPVacature} from "../../../entities/vacature/mPVacature";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-opleiding-form',
  templateUrl: './opleiding-form.component.html',
  styleUrls: ['./opleiding-form.component.css']
})
export class OpleidingFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  codeNiveauOpleidingValues = codeNiveauOpleidingValues;
  indicatieDiplomaValues = indicatieDiplomaValues;

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();


  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      opleiding: this.fb.array([])
    })

    this.subscription.add(
      this.form.controls.opleiding.valueChanges.subscribe((value) => {
        this.valueChangeVacature.emit({
          opleiding: value
        });
      })
    );

    this.formReady.emit(this.t);
  }

  addOpleiding() {
    this.t.push(this.fb.group({
      codeNiveauOpleiding: ['', [Validators.required]],
      indicatieDiploma: ['', [Validators.required]],
      opleidingsnaam: this.fb.group({})
    }));
  }

  removeOpleiding(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.opleiding as FormArray;
  }

  updateOpleidingsnaam(opleidingControl: FormGroup, group: FormGroup) {
    opleidingControl.setControl('opleidingsnaam', group);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
