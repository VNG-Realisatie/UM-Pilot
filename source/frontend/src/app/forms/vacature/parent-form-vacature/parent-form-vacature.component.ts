import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {VacatureBemiddelaarService} from "../../../services/vacature-bemiddelaar.service";
import {MatchesRequestGemeente} from "../../../entities/shared/matchesRequestGemeente";
import {MPVacature} from "../../../entities/vacature/mPVacature";
import {NotificationService} from "../../../services/notification.service";
import {finalize} from "rxjs/operators";

@Component({
  selector: 'app-parent-vacature-form',
  templateUrl: './parent-form-vacature.component.html',
  styleUrls: ['./parent-form-vacature.component.css']
})

// Forms are handled in the manner of: https://sandroroth.com/blog/reactive-forms-data-handling
// Children forms give the Parent form a FormGroup or FormArray and handle the forms themselves.
// The Parent form bundles them.
export class ParentFormVacatureComponent implements OnInit {

  request: MatchesRequestGemeente = {
    postcode: "",
    straal: 0,
    aanvraagKenmerk: "",
    vraagObject: {}
  };

  vraagObject: MPVacature = {};
  submitted: boolean = false;

  form = this.fb.group({});

  constructor(
    private fb: FormBuilder,
    private vacatureService: VacatureBemiddelaarService,
    private notificationService: NotificationService
  ) {
  }

  ngOnInit(): void {
  }

  addChildFormGroup(name: string, group: FormGroup) {
    this.form.addControl(name, group);
  }

  addChildFormArray(name: string, array: FormArray) {
    this.form.addControl(name, array);
  }

  // To handle switching between gecodeerd and ongecodeerd forms, replace with setControl
  replaceOrAddChildFormGroup(name: string, group: FormGroup) {
    this.form.setControl(name, group);
  }

  onValueChange(changes: Partial<MPVacature>) {
    this.vraagObject = {...this.vraagObject, ...changes};
  }

  // for handling spread of postcode,straal and aanvraagKenmerk object
  onValueChangeRequestInfo(changes: Partial<{ postcode: string; straal: number; aanvraagKenmerk: string }>) {
    this.request.postcode = changes.postcode;
    this.request.straal = changes.straal;
    this.request.aanvraagKenmerk = changes.aanvraagKenmerk;
  }


  onSubmit(): void {
    this.submitted = true;
    this.request.vraagObject = this.vraagObject;
    this.vacatureService.requestVacatures(this.request)
      .pipe(
        finalize(() => {
          this.submitted = false;
        }))
      .subscribe(data => {
        if (data) {
          this.notificationService.showSuccess("Succes")
        }
      })
  }
}
