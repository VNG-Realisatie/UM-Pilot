import {format, parseISO} from 'date-fns'

export function dateFormatter(params: any) {
  return format(parseISO(params.value), "dd-MM-yyyy")
}

export function dateFormatterString(dateString: string | undefined) {
  if (dateString) {
    return format(parseISO(dateString), 'dd-MM-yyyy')
  } else {
    return dateString;
  }
}

export function dateFormatterNgbDate(ngbDate: any) {
  try {
    const myDate = new Date(ngbDate.year, ngbDate.month - 1, ngbDate.day);
    return format(myDate, "yyyy-MM-dd")
  } catch (e) {
    return ngbDate
  }
}
