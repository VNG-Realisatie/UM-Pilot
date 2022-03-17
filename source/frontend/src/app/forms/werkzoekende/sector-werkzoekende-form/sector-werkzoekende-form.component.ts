import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {MPWerkzoekende} from "../../../entities/werkzoekende/mPWerkzoekende";

@Component({
  selector: 'app-sector-werkzoekende-form',
  templateUrl: './sector-werkzoekende-form.component.html',
  styleUrls: ['./sector-werkzoekende-form.component.css']
})
export class SectorWerkzoekendeFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPWerkzoekende>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      sector: this.fb.array([]),
    });

    this.subscription.add(
      this.form.controls.sector.valueChanges.subscribe((value) => {
        this.valueChangeWerkzoekende.emit({
          sector: value
        });
      })
    );

    this.formReady.emit(this.t);
  }


  addSector() {
    this.t.push(this.fb.group({
      codeSbi: ['', [Validators.min(0), Validators.max(99999)]]
    }));
  }

  removeSector(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.sector as FormArray;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
