import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {sollicitatieWijzeValues} from "../../../forms-util/formcontrol-value-enums";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {Subscription} from "rxjs";
import {MPVacature} from "../../../entities/vacature/mPVacature";

@Component({
  selector: 'app-sollicitatiewijze-form',
  templateUrl: './sollicitatiewijze-form.component.html',
  styleUrls: ['./sollicitatiewijze-form.component.css']
})
export class SollicitatiewijzeFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  sollicitatieWijzeValues = sollicitatieWijzeValues


  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      sollicitatiewijze: this.fb.array([]),
    });

    this.subscription.add(
      this.form.controls.sollicitatiewijze.valueChanges.subscribe((value) => {
        this.valueChangeVacature.emit({
          sollicitatiewijze: value
        });
      })
    );

    this.formReady.emit(this.t);
  }


  addSollicitatiewijze() {
    if (this.t.length < sollicitatieWijzeValues.size) {
      this.t.push(this.fb.group({
        codeSollicitatiewijze: ['', [Validators.required]]
      }));
    }
  }

  removeSollicitatieWijze(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.sollicitatiewijze as FormArray;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
