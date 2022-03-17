import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AbstractBaseFormComponent} from './abstract-base-form.component';

describe('AbstractBaseFormComponent', () => {
  let component: AbstractBaseFormComponent;
  let fixture: ComponentFixture<AbstractBaseFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AbstractBaseFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AbstractBaseFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
