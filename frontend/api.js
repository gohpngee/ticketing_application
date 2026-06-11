const API_BASE_URL = "http://localhost:8080/api/v1";

async function fetchJson(path) {
  const response = await fetch(`${API_BASE_URL}${path}`);
  if (!response.ok) {
    throw new Error(`Request failed (${response.status})`);
  }
  return response.json();
}

function formatLabel(value) {
  if (!value) return "-";
  return String(value).replaceAll("_", " ");
}

function formatDate(value) {
  if (!value) return "-";
  const date = new Date(value);
  return Number.isNaN(date.getTime()) ? value : date.toLocaleString();
}

function getTicketOwnerUsername(ticket) {
  return ticket?.ticketOwner?.username ?? "Unknown";
}
