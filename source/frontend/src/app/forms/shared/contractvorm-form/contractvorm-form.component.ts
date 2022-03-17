import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {codeTypeArbeidscontractValues, codeTypeOvereenkomstValues} from "../../../forms-util/formcontrol-value-enums";
import {Subscription} from "rxjs";
import {MPVacature} from "../../../entities/vacature/mPVacature";
import {MPWerkzoekende} from "../../../entities/werkzoekende/mPWerkzoekende";

@Component({
  selector: 'app-contractvorm-form',
  templateUrl: './contractvorm-form.component.html',
  styleUrls: ['./contractvorm-form.component.css']
})
export class ContractvormFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPWerkzoekende>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  codeTypeArbeidscontractValues = codeTypeArbeidscontractValues;
  codeTypeOvereenkomstValues = codeTypeOvereenkomstValues;


  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {

    this.form = this.fb.group({
      contractvorm: this.fb.array([]),
    });

    this.subscription.add(
      this.form.controls.contractvorm.valueChanges.subscribe((value) => {
        const entity = {
          contractvorm: value
        };
        this.valueChangeVacature.emit(entity);
        this.valueChangeWerkzoekende.emit(entity);
      })
    );

    this.formReady.emit(this.t);
  }

  addContractvorm() {
    this.t.push(this.fb.group({
      codeTypeArbeidscontract: ['', [Validators.required]],
      codeTypeOvereenkomst: ['', [Validators.required]]
    }));
  }

  removeContractvorm(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.contractvorm as FormArray;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
