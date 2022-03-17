import {ComponentFixture, TestBed} from '@angular/core/testing';

import {TelefoonMatchDetailedComponent} from './telefoon-match-detailed.component';

describe('TelefoonMatchDetailedComponent', () => {
  let component: TelefoonMatchDetailedComponent;
  let fixture: ComponentFixture<TelefoonMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TelefoonMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TelefoonMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
