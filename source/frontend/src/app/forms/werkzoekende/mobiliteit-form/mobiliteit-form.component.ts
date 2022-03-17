import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MPWerkzoekende} from "../../../entities/werkzoekende/mPWerkzoekende";
import {Subscription} from "rxjs";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";

@Component({
  selector: 'app-mobiliteit-form',
  templateUrl: './mobiliteit-form.component.html',
  styleUrls: ['./mobiliteit-form.component.css']
})
export class MobiliteitFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPWerkzoekende>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      bemiddelingspostcode: ['', [Validators.pattern(/^[1-9][0-9]{3}(?!sa|sd|ss)[a-z]{2}$/i)]],
      maximaleReisafstand: ['', [Validators.min(0), Validators.max(999)]],
      maximaleReistijd: ['', [Validators.min(0), Validators.max(999)]]
    });

    this.subscription.add(
      this.form.valueChanges.subscribe((value) => {
        this.valueChangeWerkzoekende.emit({
          mobiliteit: {
            bemiddelingspostcode: value.bemiddelingspostcode,
            maximaleReisafstand: value.maximaleReisafstand,
            maximaleReistijd: value.maximaleReistijd
          }
        });
      })
    );

    this.formReady.emit(this.form);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
