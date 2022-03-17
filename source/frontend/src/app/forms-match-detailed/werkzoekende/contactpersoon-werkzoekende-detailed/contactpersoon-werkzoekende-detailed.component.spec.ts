import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ContactpersoonWerkzoekendeDetailedComponent} from './contactpersoon-werkzoekende-detailed.component';

describe('ContactpersoonWerkzoekendeDetailedComponent', () => {
  let component: ContactpersoonWerkzoekendeDetailedComponent;
  let fixture: ComponentFixture<ContactpersoonWerkzoekendeDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ContactpersoonWerkzoekendeDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactpersoonWerkzoekendeDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
