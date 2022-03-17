import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MPVacature} from "../../../entities/vacature/mPVacature";
import {Subscription} from "rxjs";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";

@Component({
  selector: 'app-vakvaardigheid-form',
  templateUrl: './vakvaardigheid-form.component.html',
  styleUrls: ['./vakvaardigheid-form.component.css']
})
export class VakvaardigheidFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPArbeidsmarktkwalificatie>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      vakvaardigheid: this.fb.array([])
    })

    this.subscription.add(
      this.form.controls.vakvaardigheid.valueChanges.subscribe((value) => {
        const entity = {
          vakvaardigheid: value
        };
        this.valueChangeVacature.emit(entity);
        this.valueChangeWerkzoekende.emit(entity);
      })
    );

    this.formReady.emit(this.t);
  }

  addVakvaardigheid() {
    this.t.push(this.fb.group({
      omschrijving: ['', [Validators.required, Validators.maxLength(120)]]
    }));

  }

  removeVakvaardigheid(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.vakvaardigheid as FormArray;
  }


  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
