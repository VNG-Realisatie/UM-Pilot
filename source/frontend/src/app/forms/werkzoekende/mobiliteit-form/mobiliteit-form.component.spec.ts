import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MobiliteitFormComponent} from './mobiliteit-form.component';

describe('MobiliteitFormComponent', () => {
  let component: MobiliteitFormComponent;
  let fixture: ComponentFixture<MobiliteitFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MobiliteitFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MobiliteitFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
