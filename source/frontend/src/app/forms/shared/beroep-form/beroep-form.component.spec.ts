import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BeroepFormComponent} from './beroep-form.component';

describe('BeroepFormComponent', () => {
  let component: BeroepFormComponent;
  let fixture: ComponentFixture<BeroepFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BeroepFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BeroepFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
