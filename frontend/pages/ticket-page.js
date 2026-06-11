document.addEventListener("DOMContentLoaded", loadTicketDetail);

async function loadTicketDetail() {
  const statusEl = document.getElementById("detailStatus");
  const contentEl = document.getElementById("ticketContent");
  const ticketId = new URLSearchParams(window.location.search).get("ticketId");

  if (!ticketId) {
    statusEl.textContent = "Missing ticket ID in the URL.";
    statusEl.classList.add("error");
    return;
  }

  try {
    const ticket = await fetchJson(`/tickets/${encodeURIComponent(ticketId)}`);

    document.getElementById("ticketTitle").textContent = ticket.ticketId + ": " + (ticket.ticketTitle ?? "Untitled");
    document.getElementById("ticketDescription").textContent =
      ticket.ticketDescription ?? "No description provided.";
    document.getElementById("ticketOwner").textContent = getOwnerName(ticket);
    document.getElementById("ticketStatus").textContent = formatLabel(ticket.ticketStatus);
    document.getElementById("ticketPriority").textContent = formatLabel(ticket.ticketPriority);
    document.getElementById("ticketCreatedAt").textContent = formatDate(ticket.createdAt);
    document.getElementById("ticketUpdatedAt").textContent = formatDate(ticket.updatedAt);

    statusEl.textContent = "";
    contentEl.classList.remove("hidden");
  } catch (error) {
    statusEl.textContent = `Could not load ticket "${ticketId}".`;
    statusEl.classList.add("error");
    console.error(error);
  }
}
