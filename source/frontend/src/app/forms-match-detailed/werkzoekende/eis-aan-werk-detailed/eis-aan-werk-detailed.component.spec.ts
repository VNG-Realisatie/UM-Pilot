import {ComponentFixture, TestBed} from '@angular/core/testing';

import {EisAanWerkDetailedComponent} from './eis-aan-werk-detailed.component';

describe('EisAanWerkDetailedComponent', () => {
  let component: EisAanWerkDetailedComponent;
  let fixture: ComponentFixture<EisAanWerkDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EisAanWerkDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EisAanWerkDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
