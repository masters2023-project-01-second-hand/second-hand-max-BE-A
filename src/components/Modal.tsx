import {
  useRef,
  useEffect,
  DialogHTMLAttributes,
  BaseSyntheticEvent,
} from 'react';
import { createPortal } from 'react-dom';
import { styled, keyframes } from 'styled-components';
import { Button } from './Button';

type ModalProps = DialogHTMLAttributes<HTMLDialogElement> & {
  isOpen: boolean;
  onClose: () => void;
  children: React.ReactNode;
  headline?: string;
};

export function Modal({ isOpen, onClose, headline, children }: ModalProps) {
  const modalRef = useRef<HTMLDialogElement>(null);
  const rootElement = document.getElementById('modal-root');
  const hasHeadline = !!headline;

  const onModalClose = (event: BaseSyntheticEvent) => {
    const modal = modalRef.current;
    if (event.target === modal) onClose();
  };

  useEffect(() => {
    const modal = modalRef.current;
    isOpen && modal?.showModal();
  }, [isOpen]);

  if (!rootElement) return;

  return createPortal(
    <Dialog ref={modalRef} onClose={onModalClose} onClick={onModalClose}>
      <Container>
        <Header $hasHeadline={hasHeadline}>
          {hasHeadline ? (
            <Headline>{headline}</Headline>
          ) : (
            <Button styledType="ghost">뒤로</Button>
          )}
          <Button styledType="ghost" onClick={onClose}>
            <Plus>X</Plus>
          </Button>
        </Header>
        <Content>{children}</Content>
      </Container>
    </Dialog>,
    rootElement
  );
}

const show = keyframes`
  from {
    opacity: 0;
    transform: translateY(min(100px, 5vh));
  }
  to {
    opacity: 1;
    transform: translateY(0%);
  }
`;

const Dialog = styled.dialog`
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
  background-color: ${({ theme }) => theme.color.neutralBackground};
  border-radius: 16px;
  border-width: 0;
  padding: 0;

  &[open] {
    animation: ${show} 250ms cubic-bezier(0.4, 0, 0.2, 1) forwards;
  }

  &::backdrop {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: ${({ theme }) => theme.color.neutralOverlay};
    animation: none;
  }
`;

const Container = styled.div`
  width: 320px;
  height: 700px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex-shrink: 0;
  overflow-x: hidden;
`;

const Header = styled.header<{ $hasHeadline: boolean }>`
  min-height: 72px;
  display: flex;
  padding: 8px 8px 16px 24px;
  justify-content: space-between;
  align-items: center;
  align-self: stretch;

  ${({ $hasHeadline }) => !$hasHeadline && 'padding: 8px 8px 16px 12px;'}
`;

const Headline = styled.h2`
  font: ${({ theme }) => theme.font.displayStrong20};
`;

const Plus = styled.div`
  display: flex;
  width: 32px;
  height: 32px;
  padding: 12px;
  justify-content: center;
  align-items: center;
`;

const Content = styled.div`
  display: flex;
  flex-direction: column;
  flex: 1 0 0;
`;