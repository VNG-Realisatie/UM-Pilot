import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {Subscription} from "rxjs";
import {MPVacature} from "../../../entities/vacature/mPVacature";

@Component({
  selector: 'app-werkervaring-form',
  templateUrl: './werkervaring-form.component.html',
  styleUrls: ['./werkervaring-form.component.css']
})
export class WerkervaringFormComponent extends AbstractBaseFormComponent implements OnInit {

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
      werkervaring: this.fb.array([])
    })

    this.subscription.add(
      this.form.controls.werkervaring.valueChanges.subscribe((value) => {
        this.valueChangeVacature.emit({
          werkervaring: value
        });
      })
    );

    this.formReady.emit(this.t);

  }

  addWerkervaring() {
    this.t.push(this.fb.group({
      aantalJarenWerkzaamInBeroep: ['', [Validators.required, Validators.min(0), Validators.max(99)]]
    }));
  }

  removeWerkervaring(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.werkervaring as FormArray;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
