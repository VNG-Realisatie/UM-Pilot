import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ButtonCellRendererFetched } from './button-cell-renderer-fetched.component';

describe('ButtonCellRendererFetchedComponent', () => {
  let component: ButtonCellRendererFetched;
  let fixture: ComponentFixture<ButtonCellRendererFetched>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ButtonCellRendererFetched ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ButtonCellRendererFetched);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
