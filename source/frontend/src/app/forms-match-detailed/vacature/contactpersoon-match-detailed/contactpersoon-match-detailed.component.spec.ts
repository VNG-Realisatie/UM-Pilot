import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ContactpersoonMatchDetailedComponent} from './contactpersoon-match-detailed.component';

describe('ContactpersoonMatchDetailedComponent', () => {
  let component: ContactpersoonMatchDetailedComponent;
  let fixture: ComponentFixture<ContactpersoonMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ContactpersoonMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactpersoonMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
