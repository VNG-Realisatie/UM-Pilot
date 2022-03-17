import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerktijdenFormComponent} from './werktijden-form.component';

describe('WerktijdenFormComponent', () => {
  let component: WerktijdenFormComponent;
  let fixture: ComponentFixture<WerktijdenFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerktijdenFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerktijdenFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
