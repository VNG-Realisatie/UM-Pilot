import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ContactgegevensWerkzoekendeDetailedComponent} from './contactgegevens-werkzoekende-detailed.component';

describe('ContactgegevensWerkzoekendeDetailedComponent', () => {
  let component: ContactgegevensWerkzoekendeDetailedComponent;
  let fixture: ComponentFixture<ContactgegevensWerkzoekendeDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ContactgegevensWerkzoekendeDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactgegevensWerkzoekendeDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
